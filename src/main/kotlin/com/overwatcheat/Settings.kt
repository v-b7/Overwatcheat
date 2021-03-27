/*
 * Free, open-source undetected color cheat for Overwatch!
 * Copyright (C) 2017  Thomas G. Nappo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.overwatcheat

import java.io.File

class Settings(
    val aimKey: Int, val sensitivity: Float, val fps: Double, val aimDurationMillis: Long, val aimJitterPercent: Int,
    val boxWidthDivisor: Float, val boxHeightDivisor: Float,
    val maxSnapDivisor: Float,
    val targetColors: IntArray, val targetColorTolerance: Int,
    val windowTitleSearch: String,
    val deviceId: Int,
    val aimOffsetX: Int, val aimOffsetY: Int
) {

    companion object {

        const val DEFAULT_FILE = "overwatcheat.cfg"

        fun read(filePath: String = DEFAULT_FILE) = read(File(filePath))

        fun read(file: File): Settings {
            var aimKey = 1
            var sensitivity = 15.0F
            var fps = 60.0
            var aimJitterPercent = 50
            var aimDurationMillis = (1000L / fps).toLong() + 1L // 17 if fps is 60

            var boxWidthDivisor = 10.0F
            var boxHeightDivisor = 12.0F

            var maxSnapDivisor = 2.0F

            var targetColors = intArrayOf(0xdd32db, 0xdb33d8, 0xe23be9, 0xd619dd, 0xd200d6)
            var targetColorTolerance = 16

            var windowTitleSearch = "Overwatch"

            var deviceId = 11

            var aimOffsetX = 34
            var aimOffsetY = 56

            file.readLines().forEach {
                if (it.contains("=")) {
                    val split = it.split("=")
                    when (split[0]) {
                        "aim_key" -> aimKey = split[1].toInt()
                        "sensitivity" -> sensitivity = split[1].toFloat()
                        "fps" -> fps = split[1].toDouble()
                        "aim_duration_millis" -> aimDurationMillis = split[1].toLong()
                        "aim_jitter_percent" -> aimJitterPercent = split[1].toInt()
                        "box_width_divisor" -> boxWidthDivisor = split[1].toFloat()
                        "box_height_divisor" -> boxHeightDivisor = split[1].toFloat()
                        "max_snap_divisor" -> maxSnapDivisor = split[1].toFloat()
                        "target_colors" -> targetColors =
                            split[1].split(',').map { i -> Integer.parseInt(i, 16) }.toIntArray()
                        "target_color_tolerance" -> targetColorTolerance = split[1].toInt()
                        "window_title_search" -> windowTitleSearch = split[1]
                        "device_id" -> deviceId = split[1].toInt()
                        "aim_offset_x" -> aimOffsetX = split[1].toInt()
                        "aim_offset_y" -> aimOffsetY = split[1].toInt()
                    }
                }
            }

            return Settings(
                aimKey,
                sensitivity,
                fps,
                aimDurationMillis,
                aimJitterPercent,
                boxWidthDivisor,
                boxHeightDivisor,
                maxSnapDivisor,
                targetColors,
                targetColorTolerance,
                windowTitleSearch,
                deviceId,
                aimOffsetX, aimOffsetY
            )
        }
    }

}