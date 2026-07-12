package com.materialkobweb.utils

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotSame
import kotlin.test.assertSame

/**
 * Unit tests for ViewModelStore to verify LRU (Least Recently Used) eviction behavior.
 *
 * These tests document and verify:
 * 1. Basic caching behavior (same key returns same instance)
 * 2. LRU eviction when MAX_STORE_SIZE is exceeded
 * 3. Access order tracking (recently accessed items are preserved)
 * 4. Remove and clear operations
 */
class ViewModelStoreTest {

    // Simple test ViewModel class
    private class TestViewModel(val id: String) : ViewModel()

    /**
     * Reset the store before each test to ensure isolation.
     * Note: ViewModelStore is a singleton, so we need to clear it between tests.
     */
    private fun resetStore() {
        ViewModelStore.clear()
    }

    @BeforeTest
    fun setup() {
        Logger.init("development")
    }

    @Test
    fun testGetOrCreate_cachesSameInstance() {
        resetStore()

        // Given: A ViewModel is created with a specific key
        val vm1 = ViewModelStore.getOrCreate("test-key") { TestViewModel("vm1") }

        // When: We request the same key again
        val vm2 = ViewModelStore.getOrCreate("test-key") { TestViewModel("vm2") }

        // Then: The same instance should be returned (factory not called again)
        assertSame(vm1, vm2, "Should return the same instance for the same key")
        assertEquals("vm1", vm2.id, "Should use the original instance, not create a new one")
    }

    @Test
    fun testGetOrCreate_differentKeysCreateDifferentInstances() {
        resetStore()

        // Given: Two different keys
        val vm1 = ViewModelStore.getOrCreate("key1") { TestViewModel("vm1") }
        val vm2 = ViewModelStore.getOrCreate("key2") { TestViewModel("vm2") }

        // Then: Different instances should be returned
        assertNotSame(vm1, vm2, "Different keys should create different instances")
        assertEquals("vm1", vm1.id)
        assertEquals("vm2", vm2.id)
    }

    @Test
    fun testLruEviction_whenMaxSizeExceeded() {
        resetStore()

        // Given: MAX_STORE_SIZE is 50, we create 51 ViewModels
        val keys = (1..51).map { "key-$it" }
        val viewModels = keys.map { key ->
            ViewModelStore.getOrCreate(key) { TestViewModel(key) }
        }

        // When: We try to access the first key (which should have been evicted)
        val newVm1 = ViewModelStore.getOrCreate("key-1") { TestViewModel("key-1-new") }

        // Then: A new instance should be created (LRU eviction occurred)
        assertEquals("key-1-new", newVm1.id, "First key should have been evicted and recreated")
        assertNotSame(viewModels[0], newVm1, "Should be a new instance, not the original")

        // And: The most recently added items should still be cached
        val vm51 = ViewModelStore.getOrCreate("key-51") { TestViewModel("key-51-new") }
        assertSame(viewModels[50], vm51, "Most recent key should still be cached")
    }

    @Test
    fun testLruEviction_accessOrderMatters() {
        resetStore()

        // Given: We create MAX_STORE_SIZE ViewModels
        val keys = (1..50).map { "key-$it" }
        val viewModels = keys.map { key ->
            ViewModelStore.getOrCreate(key) { TestViewModel(key) }
        }

        // When: We access key-1 again (making it most recently used)
        val vm1Accessed = ViewModelStore.getOrCreate("key-1") { TestViewModel("key-1-new") }
        assertSame(viewModels[0], vm1Accessed, "Should return cached instance")

        // And: We add a new key (this should evict key-2, which is now the LRU)
        ViewModelStore.getOrCreate("key-51") { TestViewModel("key-51") }

        // Then: key-1 should still be cached (it was recently accessed)
        val vm1AfterEviction = ViewModelStore.getOrCreate("key-1") { TestViewModel("key-1-new-2") }
        assertSame(viewModels[0], vm1AfterEviction, "key-1 should still be cached due to recent access")

        // But: key-2 should have been evicted (it was the LRU)
        val vm2AfterEviction = ViewModelStore.getOrCreate("key-2") { TestViewModel("key-2-new") }
        assertEquals("key-2-new", vm2AfterEviction.id, "key-2 should have been evicted and recreated")
        assertNotSame(viewModels[1], vm2AfterEviction, "key-2 should be a new instance")
    }

    @Test
    fun testRemove_removesFromCache() {
        resetStore()

        // Given: A cached ViewModel
        val vm1 = ViewModelStore.getOrCreate("test-key") { TestViewModel("vm1") }

        // When: We remove it from the cache
        ViewModelStore.remove("test-key")

        // Then: A new instance should be created when requested again
        val vm2 = ViewModelStore.getOrCreate("test-key") { TestViewModel("vm2") }
        assertNotSame(vm1, vm2, "Should create a new instance after removal")
        assertEquals("vm2", vm2.id)
    }

    @Test
    fun testClear_removesAllFromCache() {
        resetStore()

        // Given: Multiple cached ViewModels
        val vm1 = ViewModelStore.getOrCreate("key1") { TestViewModel("vm1") }
        val vm2 = ViewModelStore.getOrCreate("key2") { TestViewModel("vm2") }
        val vm3 = ViewModelStore.getOrCreate("key3") { TestViewModel("vm3") }

        // When: We clear the cache
        ViewModelStore.clear()

        // Then: All instances should be recreated
        val newVm1 = ViewModelStore.getOrCreate("key1") { TestViewModel("vm1-new") }
        val newVm2 = ViewModelStore.getOrCreate("key2") { TestViewModel("vm2-new") }
        val newVm3 = ViewModelStore.getOrCreate("key3") { TestViewModel("vm3-new") }

        assertNotSame(vm1, newVm1)
        assertNotSame(vm2, newVm2)
        assertNotSame(vm3, newVm3)
    }

    @Test
    fun testSize_tracksCorrectly() {
        resetStore()

        // Given: Empty store
        assertEquals(0, ViewModelStore.size(), "Store should be empty initially")

        // When: We add ViewModels
        ViewModelStore.getOrCreate("key1") { TestViewModel("vm1") }
        assertEquals(1, ViewModelStore.size())

        ViewModelStore.getOrCreate("key2") { TestViewModel("vm2") }
        assertEquals(2, ViewModelStore.size())

        // And: We access an existing key (should not increase size)
        ViewModelStore.getOrCreate("key1") { TestViewModel("vm1-new") }
        assertEquals(2, ViewModelStore.size(), "Size should not increase for existing keys")

        // And: We remove one
        ViewModelStore.remove("key1")
        assertEquals(1, ViewModelStore.size())

        // And: We clear all
        ViewModelStore.clear()
        assertEquals(0, ViewModelStore.size())
    }

    @Test
    fun testLruEviction_maintainsCorrectSize() {
        resetStore()

        // Given: We add MAX_STORE_SIZE + 10 ViewModels
        for (i in 1..60) {
            ViewModelStore.getOrCreate("key-$i") { TestViewModel("vm-$i") }
        }

        // Then: Size should be capped at MAX_STORE_SIZE (50)
        assertEquals(50, ViewModelStore.size(), "Store size should be capped at MAX_STORE_SIZE")
    }

    @Test
    fun testRealWorldScenario_searchSectionUsage() {
        resetStore()

        // Simulate user navigation through search pages
        val searchQueries = listOf(
            "searchSection-/search?query=restaurant&lat=52.37&lng=4.89&radius=5",
            "searchSection-/search?query=restaurant&lat=52.37&lng=4.89&radius=10",
            "searchSection-/search?query=cafe&lat=51.92&lng=4.48&radius=5",
            "searchSection-/search?query=bar&lat=52.37&lng=4.89&radius=5",
        )

        // Given: User performs multiple searches
        val viewModels = searchQueries.map { query ->
            ViewModelStore.getOrCreate(query) { TestViewModel(query) }
        }

        // When: User goes back to first search
        val vm1Again = ViewModelStore.getOrCreate(searchQueries[0]) { TestViewModel("new-instance") }

        // Then: Original instance should be returned (cached)
        assertSame(viewModels[0], vm1Again, "Search history should be preserved")

        // And: All searches should still be cached
        assertEquals(4, ViewModelStore.size())
    }

    @Test
    fun testCompanyPageNavigation() {
        resetStore()

        // Simulate user browsing multiple company pages
        val companyIds = (1..10).map { "company-$it" }
        val staticPages = listOf("home", "login", "profile")

        // Given: User visits static pages
        val staticViewModels = staticPages.map { page ->
            ViewModelStore.getOrCreate(page) { TestViewModel(page) }
        }

        // And: User visits company pages
        companyIds.forEach { id ->
            ViewModelStore.getOrCreate(id) { TestViewModel(id) }
        }

        // Then: All should be cached (13 total, well under limit)
        assertEquals(13, ViewModelStore.size())

        // When: User goes back to home
        val homeVm = ViewModelStore.getOrCreate("home") { TestViewModel("home-new") }

        // Then: Home ViewModel should still be cached (same instance)
        assertSame(staticViewModels[0], homeVm, "Static pages should remain cached")
        assertEquals("home", homeVm.id, "ViewModel ID should be unchanged")
    }
}
