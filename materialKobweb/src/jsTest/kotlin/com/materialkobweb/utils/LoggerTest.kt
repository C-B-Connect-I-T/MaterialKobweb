package com.materialkobweb.utils

import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Unit tests for Logger to verify environment-aware logging behavior.
 *
 * These tests spy on console methods to verify that:
 * 1. Logging IS suppressed in production
 * 2. Logging IS NOT suppressed in development/staging
 * 3. Message formatting is correct
 */
class LoggerTest {
    private val consoleLogCalls = mutableListOf<String>()
    private val consoleInfoCalls = mutableListOf<String>()
    private val consoleWarnCalls = mutableListOf<String>()
    private val consoleErrorCalls = mutableListOf<String>()

    private var originalLog: dynamic = null
    private var originalInfo: dynamic = null
    private var originalWarn: dynamic = null
    private var originalError: dynamic = null

    @BeforeTest
    fun setup() {
        // Capture original console methods
        originalLog = console.asDynamic().log
        originalInfo = console.asDynamic().info
        originalWarn = console.asDynamic().warn
        originalError = console.asDynamic().error

        // Spy on console methods using asDynamic()
        console.asDynamic().log = { message: String ->
            consoleLogCalls.add(message)
        }
        console.asDynamic().info = { message: String ->
            consoleInfoCalls.add(message)
        }
        console.asDynamic().warn = { message: String ->
            consoleWarnCalls.add(message)
        }
        console.asDynamic().error = { message: String ->
            consoleErrorCalls.add(message)
        }
    }

    @AfterTest
    fun teardown() {
        // Restore original console methods
        console.asDynamic().log = originalLog
        console.asDynamic().info = originalInfo
        console.asDynamic().warn = originalWarn
        console.asDynamic().error = originalError

        // Clear spy collections
        consoleLogCalls.clear()
        consoleInfoCalls.clear()
        consoleWarnCalls.clear()
        consoleErrorCalls.clear()
    }

    @Test
    fun shouldLogDebugInDevelopment() {
        // Given: Development environment
        Logger.init("development")

        consoleLogCalls.clear()

        // When: Calling debug
        Logger.debug("TestTag", "Debug message")

        // Then: console.log should be called with formatted message
        assertEquals(1, consoleLogCalls.size, "console.log should be called once")
        assertTrue(consoleLogCalls[0].contains("[TestTag]"), "Message should contain tag")
        assertTrue(consoleLogCalls[0].contains("Debug message"), "Message should contain content")
    }

    @Test
    fun shouldLogInfoInStaging() {
        // Given: Staging environment
        Logger.init("staging")

        consoleInfoCalls.clear()

        // When: Calling info
        Logger.info("Tag1", "Info message")

        // Then: console.info should be called
        assertEquals(1, consoleInfoCalls.size, "console.info should be called once")
        assertTrue(consoleInfoCalls[0].contains("[Tag1]"), "Message should contain tag")
    }

    @Test
    fun shouldNotLogInProduction() {
        // Given: Production environment
        Logger.init("production")

        consoleLogCalls.clear()
        consoleInfoCalls.clear()
        consoleWarnCalls.clear()
        consoleErrorCalls.clear()

        // When: Calling all log levels
        Logger.debug("Tag", "Debug message")
        Logger.info("Tag", "Info message")
        Logger.warn("Tag", "Warning message")
        Logger.error("Tag", "Error message")

        // Then: No console methods should be called
        assertEquals(0, consoleLogCalls.size, "console.log should NOT be called in production")
        assertEquals(0, consoleInfoCalls.size, "console.info should NOT be called in production")
        assertEquals(0, consoleWarnCalls.size, "console.warn should NOT be called in production")
        assertEquals(0, consoleErrorCalls.size, "console.error should NOT be called in production")
    }

    @Test
    fun shouldFormatMessageWithTag() {
        // Given: Development environment
        Logger.init("development")

        consoleLogCalls.clear()

        // When: Calling debug with tag
        Logger.debug("MyTag", "My message")

        // Then: Message should be formatted as [MyTag] My message
        assertEquals(1, consoleLogCalls.size)
        assertEquals("[MyTag] My message", consoleLogCalls[0])
    }

    @Test
    fun shouldHandleDebugWithoutTag() {
        // Given: Development environment
        Logger.init("development")

        consoleLogCalls.clear()

        // When: Calling debug without tag
        Logger.debug("Simple message")

        // Then: Message should not be formatted
        assertEquals(1, consoleLogCalls.size)
        assertEquals("Simple message", consoleLogCalls[0])
    }

    @Test
    fun shouldCallCorrectConsoleMethod() {
        // Given: Development environment
        Logger.init("development")

        consoleLogCalls.clear()
        consoleWarnCalls.clear()
        consoleErrorCalls.clear()

        // When: Calling different log levels
        Logger.debug("debug message")
        Logger.warn("warn message")
        Logger.error("error message")

        // Then: Correct console methods should be called
        assertEquals(1, consoleLogCalls.size, "debug should call console.log")
        assertEquals(1, consoleWarnCalls.size, "warn should call console.warn")
        assertEquals(1, consoleErrorCalls.size, "error should call console.error")
    }

    @Test
    fun shouldRespectEnvironmentChanges() {
        // Given: Production environment
        Logger.init("production")

        consoleLogCalls.clear()

        // When: Logging in production (should be suppressed)
        Logger.debug("Production message")
        assertEquals(0, consoleLogCalls.size, "Should be suppressed in production")

        // And: Switching to development
        Logger.init("development")

        Logger.debug("Development message")

        // Then: Logging should now work
        assertEquals(1, consoleLogCalls.size, "Should be logged in development")
    }
}








