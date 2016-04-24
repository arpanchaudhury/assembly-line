package constants

import java.io.File

object UserMassages {
  def WelcomeMessage(file: File) =
    s"""
      |Welcome to Assembly Line
      |------------------------
      |
      |We are processing ${file.getName} ...""".stripMargin

  val ExitMessage =
    s"""
       |have a nice day ...
       """.stripMargin

  val ArgumentMissingMessage =
    s"""
       |You haven't provided input file path as an argument
       |
       |Usage: run <absolute-file-path>
       """.stripMargin

  def InputMessage(input: String) =
    s"""
       |Input data
       |----------
       |
       |$input""".stripMargin

  def OutputMessage(output: String) =
    s"""
       |Processed data
       |--------------
       |
       |$output""".stripMargin
}
