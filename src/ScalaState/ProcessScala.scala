package ScalaState

import KotlinClass.{Project, Task}
import KotlinHelpers.Helpers

import java.util.Calendar
import java.text.SimpleDateFormat
import scala.collection.mutable.ArrayBuffer

class ProcessScala {

  val helper = new Helpers()
  val project = new Project()
  val task = new Task()

  def processScala(projectID: String): Array[String] ={

    var countnewtaskarraydata = 0

    for(index <- 0 until helper.readAllFile(task.filename()).length){
      if(helper.readAll(1, task.filename())(index) == projectID){
        countnewtaskarraydata += 1
      }
    }

    var newtaskElementArray = new Array[String](countnewtaskarraydata)
    val taskarray_element = new Array[String](countnewtaskarraydata)
    val taskarray_index = new Array[Int](countnewtaskarraydata)
    val taskarray_predecessor = new Array[String](countnewtaskarraydata)
    val durationarray = new Array[Int](countnewtaskarraydata)
    val ES = new Array[Int](countnewtaskarraydata)
    val EF = new Array[Int](countnewtaskarraydata)
    val SuccessorArr = new Array[String](countnewtaskarraydata)
    val LF = new Array[Int](countnewtaskarraydata)
    val LS = new Array[Int](countnewtaskarraydata)

    var inctemp = 0

    for(index <- 0 until helper.readAllFile(task.filename()).length){
      if(helper.readAll(1, task.filename())(index) == projectID){
        newtaskElementArray(index) = helper.readAllFile(task.filename())(index)
      }
    }

    var temp : Int = 0
    var data : Int = 0
    var LS_value = 0
    val longestPath = ArrayBuffer[Int]()
    var longestPathValue : Int = 0
    var projectDuration = 0

    var EF_value:Int = 0
    var ES_value:Int = 0
    var incTempArry : Int = 0

    for (index <- 0 until newtaskElementArray.length) {
      val predecesssor : String = helper.readAllInarray(7,newtaskElementArray)(index)
      val task_element : String = helper.readAllInarray(0, newtaskElementArray)(index)
      val duration : Int = helper.readAllInarray(6, newtaskElementArray)(index).toInt

      taskarray_element(index) = task_element
      taskarray_predecessor(index) = predecesssor
      taskarray_index(index) = index
      durationarray(index) = duration

      if(predecesssor == "none"){
        EF_value = duration + ES_value
      }else{
        if(predecesssor.contains('.')){
          var tempArry = ArrayBuffer[Int]()
          for (i <- 0 until predecesssor.split('.').size) {
            for (j <- 0 until taskarray_element.size) {
              var data = predecesssor.split('.')(i)
              var dataj = taskarray_element(j)
              if(dataj == data){
                tempArry += EF(j)
              }else{
                tempArry += 0
              }
            }
          }
          ES_value = tempArry.max
          EF_value = duration + ES_value
        }else{
          for (i <- 0 until taskarray_element.length) {
            val data = taskarray_element(i)
            if(data == predecesssor){
              ES_value = EF(i)
              EF_value = duration + ES_value
            }
          }
        }
      }
      EF(index) = EF_value
      ES(index) = ES_value
    }

    var tempdatainc = 0
    for (i <- 0 until taskarray_element.length) {
      val datai = taskarray_element(i)
      for (j <- 0 until taskarray_predecessor.length) {
        val dataj = taskarray_predecessor(j)
        if(dataj.contains(datai)){
          data = j
          tempdatainc += 1
          if(tempdatainc > 1){
            SuccessorArr(i) = SuccessorArr(i) + "." + taskarray_element(data)
          }else{
            SuccessorArr(i) = taskarray_element(data)
          }
        }
      }
      tempdatainc = 0
    }

    for (i <- 0 until SuccessorArr.length) {
      if(data.toString() != "none"){
        longestPath += EF(i)
      }
    }

    for (i <- 0 until longestPath.length) {
      if(longestPath(i) == longestPath.max){
        longestPathValue = longestPath(i)
      }
    }

    projectDuration = longestPathValue
    var size = taskarray_element.size - 1

    for (i <- 0 until taskarray_element.length) {
      if(SuccessorArr(size) == null){
        LF(size) = longestPathValue
        LS_value = longestPathValue - durationarray(size)
        LS(size) = LS_value
      }else{
        if(SuccessorArr(size).contains('.')){
          val tempArry = ArrayBuffer[Int]()
          for (i <- 0 until SuccessorArr(size).split('.').size) {
            for (j <- 0 until taskarray_element.length) {
              var data = SuccessorArr(size).split('.')(i)
              var dataj = taskarray_element(j)
              if(dataj == data){
                tempArry += LS(j)
                println(LS(j))
              }
            }
          }
          longestPathValue = tempArry.min
          LF(size) = longestPathValue
          LS_value = longestPathValue - durationarray(size)
          LS(size) = LS_value
        }else{
          for (i <- 0 until taskarray_element.length) {
            val data = taskarray_element(i)
            if(data == SuccessorArr(size)){
              longestPathValue = LS(i)
              LF(size) = longestPathValue
              LS_value = longestPathValue - durationarray(size)
              LS(size) = LS_value
            }
          }
        }
      }
      size = size - 1
    }

    val Slack = new Array[Int](countnewtaskarraydata)
    val CriticalPath = new Array[Int](countnewtaskarraydata)
    var task_slack : Int = 0
    for (i <- 0 until taskarray_element.length) {
      task_slack = LS(i) - ES(i)
      Slack(i) = task_slack
      if(task_slack == 0){
        CriticalPath(i) = i
      }
    }
    /*
        project name
        duration
        end date
         */
    var projectdate = ""
    var projectname = ""
    var time = ""
    var dateContent = ""
    val display = new Array[String](3)

    for (i <- 0 until  helper.readAll(0, project.filename()).length) {
      val data_i =  helper.readAll(0, project.filename())(i)
      if(data_i == projectID){
        projectdate = helper.readAll(3, project.filename())(i)
        projectname = helper.readAll(1, project.filename())(i)
        time = projectdate.split(" ")(1)
        dateContent = projectdate.split(" ")(0)
        dateContent = dateContent.split("/")(0) + "-" + dateContent.split("/")(1) + "-" + dateContent.split("/")(2)

        val calendar = Calendar.getInstance()
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dateContent))
        calendar.add(Calendar.DAY_OF_MONTH, projectDuration)
        dateContent = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())

        display(0) = projectname
        display(1) = projectDuration.toString()
        display(2) = dateContent + " " + time
      }
    }
    return display
  }
}

/*
    place this at the bottom of the function for debugging
    println("EF -> " + EF.mkString(" "))
    println("ES ->"  + ES.mkString(" "))
    println("D -> " + durationarray.mkString(" "))
    println("P -> " + taskarray_predecessor.mkString(" "))
    println("T -> " + taskarray_element.mkString(" "))
    println("S -> " + SuccessorArr.mkString(" "))
    println("LF -> " + LF.mkString(" "))
    println("LS -> " + LS.mkString(" "))
    println("CP -> " + CriticalPath.mkString(" "))
    println("project Duration -> " + projectDuration)
 */