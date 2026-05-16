@file:Suppress("MagicNumber", "ForbiddenComment", "UnusedPrivateProperty", "LongParameterList")

package com.materialdesignsystem.sample

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.materialdesignsystem.theme.darkColorScheme
import com.materialdesignsystem.theme.lightColorScheme
import com.varabyte.kobweb.silk.theme.colors.ColorMode

val staticAcceptColor = Color.rgb(0x3E8E4D)
val staticPauseColor = Color.rgb(0xE2B43C)
val staticDeclineColor = Color.rgb(0xC14A35)
val staticUnknownColor = Color.rgb(0x82B3FF)

private val SuccessColorLight = Color.rgb(0xCFEBC1)
private val OnSuccessColorLight = Color.rgb(0x364D2E)
private val WarningColorLight = Color.rgb(0xFFDEA8)
private val OnWarningColorLight = Color.rgb(0x5E4200)
private val InfoColorLight = Color.rgb(0xA7C7E7)
private val OnInfoColorLight = Color.rgb(0x001C3B)

private val SuccessColorDark = Color.rgb(0x364D2E)
private val OnSuccessColorDark = Color.rgb(0xCFEBC1)
private val WarningColorDark = Color.rgb(0x5E4200)
private val OnWarningColorDark = Color.rgb(0xFFDEA8)
private val InfoColorDark = Color.rgb(0x001C3B)
private val OnInfoColorDark = Color.rgb(0xA7C7E7)

val ColorMode.SuccessColor get() = if (this == ColorMode.LIGHT) SuccessColorLight else SuccessColorDark
val ColorMode.OnSuccessColor get() = if (this == ColorMode.LIGHT) OnSuccessColorLight else OnSuccessColorDark
val ColorMode.WarningColor get() = if (this == ColorMode.LIGHT) WarningColorLight else WarningColorDark
val ColorMode.OnWarningColor get() = if (this == ColorMode.LIGHT) OnWarningColorLight else OnWarningColorDark
val ColorMode.InfoColor get() = if (this == ColorMode.LIGHT) InfoColorLight else InfoColorDark
val ColorMode.OnInfoColor get() = if (this == ColorMode.LIGHT) OnInfoColorLight else OnInfoColorDark

// Source color: #8A661C

private val PrimaryLight = Color.rgb(0x7B580C)
private val OnPrimaryLight = Color.rgb(0xFFFFFF)
private val PrimaryContainerLight = Color.rgb(0xFFDEA8)
private val OnPrimaryContainerLight = Color.rgb(0x5E4200)
private val SecondaryLight = Color.rgb(0x6D5C3F)
private val OnSecondaryLight = Color.rgb(0xFFFFFF)
private val SecondaryContainerLight = Color.rgb(0xF7DFBB)
private val OnSecondaryContainerLight = Color.rgb(0x54442A)
private val TertiaryLight = Color.rgb(0x4D6544)
private val OnTertiaryLight = Color.rgb(0xFFFFFF)
private val TertiaryContainerLight = Color.rgb(0xCFEBC1)
private val OnTertiaryContainerLight = Color.rgb(0x364D2E)
private val ErrorLight = Color.rgb(0xBA1A1A)
private val OnErrorLight = Color.rgb(0xFFFFFF)
private val ErrorContainerLight = Color.rgb(0xFFDAD6)
private val OnErrorContainerLight = Color.rgb(0x93000A)
private val BackgroundLight = Color.rgb(0xFFF8F3)
private val OnBackgroundLight = Color.rgb(0x201B13)
private val SurfaceLight = Color.rgb(0xFFF8F3)
private val OnSurfaceLight = Color.rgb(0x201B13)
private val SurfaceVariantLight = Color.rgb(0xEEE1CF)
private val OnSurfaceVariantLight = Color.rgb(0x4E4639)
private val OutlineLight = Color.rgb(0x807667)
private val OutlineVariantLight = Color.rgb(0xD1C5B4)
private val ScrimLight = Color.rgb(0x000000)
private val InverseSurfaceLight = Color.rgb(0x353027)
private val InverseOnSurfaceLight = Color.rgb(0xFAEFE2)
private val InversePrimaryLight = Color.rgb(0xEEC06D)
private val SurfaceDimLight = Color.rgb(0xE3D8CC)
private val SurfaceBrightLight = Color.rgb(0xFFF8F3)
private val SurfaceContainerLowestLight = Color.rgb(0xFFFFFF)
private val SurfaceContainerLowLight = Color.rgb(0xFDF2E5)
private val SurfaceContainerLight = Color.rgb(0xF7ECDF)
private val SurfaceContainerHighLight = Color.rgb(0xF2E7D9)
private val SurfaceContainerHighestLight = Color.rgb(0xECE1D4)

private val PrimaryDark = Color.rgb(0xEEC06D)
private val OnPrimaryDark = Color.rgb(0x422D00)
private val PrimaryContainerDark = Color.rgb(0x5E4200)
private val OnPrimaryContainerDark = Color.rgb(0xFFDEA8)
private val SecondaryDark = Color.rgb(0xDAC3A0)
private val OnSecondaryDark = Color.rgb(0x3C2E15)
private val SecondaryContainerDark = Color.rgb(0x54442A)
private val OnSecondaryContainerDark = Color.rgb(0xF7DFBB)
private val TertiaryDark = Color.rgb(0xB3CEA6)
private val OnTertiaryDark = Color.rgb(0x203619)
private val TertiaryContainerDark = Color.rgb(0x364D2E)
private val OnTertiaryContainerDark = Color.rgb(0xCFEBC1)
private val ErrorDark = Color.rgb(0xFFB4AB)
private val OnErrorDark = Color.rgb(0x690005)
private val ErrorContainerDark = Color.rgb(0x93000A)
private val OnErrorContainerDark = Color.rgb(0xFFDAD6)
private val BackgroundDark = Color.rgb(0x17130B)
private val OnBackgroundDark = Color.rgb(0xECE1D4)
private val SurfaceDark = Color.rgb(0x17130B)
private val OnSurfaceDark = Color.rgb(0xECE1D4)
private val SurfaceVariantDark = Color.rgb(0x4E4639)
private val OnSurfaceVariantDark = Color.rgb(0xD1C5B4)
private val OutlineDark = Color.rgb(0x9A8F80)
private val OutlineVariantDark = Color.rgb(0x4E4639)
private val ScrimDark = Color.rgb(0x000000)
private val InverseSurfaceDark = Color.rgb(0xECE1D4)
private val InverseOnSurfaceDark = Color.rgb(0x353027)
private val InversePrimaryDark = Color.rgb(0x7B580C)
private val SurfaceDimDark = Color.rgb(0x17130B)
private val SurfaceBrightDark = Color.rgb(0x3E382F)
private val SurfaceContainerLowestDark = Color.rgb(0x120E07)
private val SurfaceContainerLowDark = Color.rgb(0x201B13)
private val SurfaceContainerDark = Color.rgb(0x241F17)
private val SurfaceContainerHighDark = Color.rgb(0x2F2921)
private val SurfaceContainerHighestDark = Color.rgb(0x3A342B)

val KsLightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = OnPrimaryLight,
    primaryContainer = PrimaryContainerLight,
    onPrimaryContainer = OnPrimaryContainerLight,
    secondary = SecondaryLight,
    onSecondary = OnSecondaryLight,
    secondaryContainer = SecondaryContainerLight,
    onSecondaryContainer = OnSecondaryContainerLight,
    tertiary = TertiaryLight,
    onTertiary = OnTertiaryLight,
    tertiaryContainer = TertiaryContainerLight,
    onTertiaryContainer = OnTertiaryContainerLight,
    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,
    onErrorContainer = OnErrorContainerLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    surfaceTint = PrimaryLight,
    outline = OutlineLight,
    outlineVariant = OutlineVariantLight,
    scrim = ScrimLight,
    inverseSurface = InverseSurfaceLight,
    inverseOnSurface = InverseOnSurfaceLight,
    inversePrimary = InversePrimaryLight,
    surfaceDim = SurfaceDimLight,
    surfaceBright = SurfaceBrightLight,
    surfaceContainerLowest = SurfaceContainerLowestLight,
    surfaceContainerLow = SurfaceContainerLowLight,
    surfaceContainer = SurfaceContainerLight,
    surfaceContainerHigh = SurfaceContainerHighLight,
    surfaceContainerHighest = SurfaceContainerHighestLight,
)

val KsDarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    onPrimary = OnPrimaryDark,
    primaryContainer = PrimaryContainerDark,
    onPrimaryContainer = OnPrimaryContainerDark,
    secondary = SecondaryDark,
    onSecondary = OnSecondaryDark,
    secondaryContainer = SecondaryContainerDark,
    onSecondaryContainer = OnSecondaryContainerDark,
    tertiary = TertiaryDark,
    onTertiary = OnTertiaryDark,
    tertiaryContainer = TertiaryContainerDark,
    onTertiaryContainer = OnTertiaryContainerDark,
    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    surfaceTint = PrimaryDark,
    outline = OutlineDark,
    outlineVariant = OutlineVariantDark,
    scrim = ScrimDark,
    inverseSurface = InverseSurfaceDark,
    inverseOnSurface = InverseOnSurfaceDark,
    inversePrimary = InversePrimaryDark,
    surfaceDim = SurfaceDimDark,
    surfaceBright = SurfaceBrightDark,
    surfaceContainerLowest = SurfaceContainerLowestDark,
    surfaceContainerLow = SurfaceContainerLowDark,
    surfaceContainer = SurfaceContainerDark,
    surfaceContainerHigh = SurfaceContainerHighDark,
    surfaceContainerHighest = SurfaceContainerHighestDark,
)
