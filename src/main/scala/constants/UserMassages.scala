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
