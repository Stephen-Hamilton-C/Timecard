package command

import ClockedInException
import core.Color.green
import core.Color.yellow
import core.TimeEntries
import core.Util
import kotlinx.datetime.LocalTime
import kotlin.system.exitProcess

class ClockInCommand : ClockCommand() {
	override val name = "IN"
	override val description: String
		get() = TODO("Not yet implemented")
	override val shortDescription: String
		get() = TODO("Not yet implemented")
	
	override val invalidPastTimeMessage = "Time provided is before last clock out time! Use 'timecard log' to see when last clock out was."
	
	override fun clockExecute(timeEntries: TimeEntries, time: LocalTime) {
		try {
			timeEntries.clockIn(time)
			println("Clocked ${green("IN")} at ${green(Util.formatHours(time))}.")
		} catch(cie: ClockedInException) {
			println(yellow("Already clocked in! Use 'timecard out' to clock out, or use 'timecard undo' to remove last clock in."))
			exitProcess(1)
		}
	}
}