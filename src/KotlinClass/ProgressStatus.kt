package KotlinClass

import KotlinHelpers.Helpers
import java.text.SimpleDateFormat
import java.util.*


class ProgressStatus {

    val helper = Helpers()
    val project = Project()
    val task = Task()

    var project_durationFile: String = "src/Files/project_duration"

    fun ProcessKotlin(projectID : String): Array<String>{

        var countnewtaskarraydata = 0
        helper.readAllFile(task.filename()).forEachIndexed{ index, line ->
            if(helper.readAll(1, task.filename())[index] == projectID){
                countnewtaskarraydata++
            }
        }

        var newtaskElementArray = Array<String>(countnewtaskarraydata) {""}
        var taskarray_element = Array<String?>(countnewtaskarraydata) {null}
        var taskarray_index = Array<Int?>(countnewtaskarraydata) {null}
        var taskarray_predecessor = Array<String?>(countnewtaskarraydata) {null}
        var durationarray = Array<Int?>(countnewtaskarraydata) {null}
        var ES = Array<Int?>(countnewtaskarraydata) {null}
        var EF = Array<Int?>(countnewtaskarraydata) {null}
        var SuccessorArr = Array<String?>(countnewtaskarraydata) {null}
        var LF = Array<Int?>(countnewtaskarraydata) {null}
        var LS = Array<Int?>(countnewtaskarraydata) {null}

        var inctemp = 0

        helper.readAllFile(task.filename()).forEachIndexed{ index, line ->
            if(helper.readAll(1, task.filename())[index] == projectID){
                newtaskElementArray[inctemp] = line
                inctemp++
            }
        }

        var temp : Int = 0
        var data : Int = 0
        var LS_value = 0
        var longestPath: MutableList<Int> = ArrayList()
        var longestPathValue = 0
        var projectDuration = 0

        var EF_value:Int = 0
        var ES_value:Int = 0

        newtaskElementArray.forEachIndexed{ index, line ->
            var predecesssor : String = helper.readAllInarray(7, newtaskElementArray)[index]
            var task_element : String = helper.readAllInarray(0, newtaskElementArray)[index]
            var duration : Int = helper.readAllInarray(6, newtaskElementArray)[index].toInt()

            taskarray_element[index] = task_element
            taskarray_predecessor[index] = predecesssor
            taskarray_index[index] = index
            durationarray[index] = duration

            if(predecesssor == "none"){
                EF_value = duration + ES_value
            }else{
                if(predecesssor.contains(".")){
                    var max = 0
                    var tempArry: MutableList<Int> = ArrayList()
                    predecesssor.split(".").forEachIndexed{ i, data ->
                        taskarray_element.forEachIndexed{ j, dataj ->
                            if(dataj == data){
                                tempArry.add(EF[j].toString().toInt())
                            }
                        }
                    }
                    ES_value = tempArry.maxOrNull().toString().toInt()
                    EF_value = duration + ES_value
                }else{
                    taskarray_element.forEachIndexed{ i, data ->
                        if(data == predecesssor){
                            ES_value = EF[i].toString().toInt()
                            EF_value = duration + ES_value
                        }
                    }
                }
            }
            EF[index] = EF_value
            ES[index] = ES_value
        }

        taskarray_element.forEachIndexed{ i, data_i ->
            taskarray_predecessor.forEachIndexed{ j, data_j ->
                if(data_j.toString().contains(data_i.toString())){
                    data = j
                    temp++
                    if(temp > 1){
                        SuccessorArr[i] =  "${SuccessorArr[i]}.${taskarray_element[data]}"
                    }else{
                        SuccessorArr[i] = taskarray_element[data]
                    }
                }
            }
            temp = 0
        }

        SuccessorArr.forEachIndexed{ i, data ->
            if(data.toString() != "none"){
                longestPath.add(EF[i].toString().toInt())
            }
        }

        longestPath.forEachIndexed{ i, data ->
            if(data == longestPath.maxOrNull()){
                longestPathValue = data
            }
        }

        projectDuration = longestPathValue

        var size = taskarray_element.size - 1

        taskarray_element.forEachIndexed{ i, data ->
            if(SuccessorArr[size].toString() == "null"){
                LF[size] = longestPathValue
                LS_value = longestPathValue - durationarray[size].toString().toInt()
                LS[size] = LS_value
            }else{
                if(SuccessorArr[size].toString().contains(".")){
                    var tempArry: MutableList<Int> = ArrayList()
                    SuccessorArr[size].toString().split(".").forEachIndexed{ i, data ->
                        taskarray_element.forEachIndexed{ j, dataj ->
                            if(dataj == data){
                                tempArry.add(LS[j].toString().toInt())
                            }
                        }
                    }
                    longestPathValue = tempArry.minOrNull().toString().toInt()
                    LF[size] = longestPathValue
                    LS_value = longestPathValue - durationarray[size].toString().toInt()
                    LS[size] = LS_value
                }else{
                    taskarray_element.forEachIndexed{ i, data ->
                        if(data == SuccessorArr[size].toString()){
                            longestPathValue = LS[i].toString().toInt()
                            LF[size] = longestPathValue
                            LS_value = longestPathValue - durationarray[size].toString().toInt()
                            LS[size] = LS_value
                        }
                    }
                }
            }
            size--
        }

        var Slack = Array<Int?>(helper.countRecord(task.filename())) {null}
        var CriticalPath = Array<Int?>(helper.countRecord(task.filename())) {null}
        var task_slack : Int = 0
        taskarray_element.forEachIndexed{ i, data_i ->
            task_slack = LS[i].toString().toInt() - ES[i].toString().toInt()
            Slack[i] = task_slack
            if(task_slack == 0){
                CriticalPath[i] = i.toString().toInt()
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
        var display = Array(3) {""}

        helper.readAll(0, project.filename()).forEachIndexed{ i, data_i ->
            if(data_i == projectID){
                projectdate = helper.readAll(3, project.filename())[i]
                projectname = helper.readAll(1, project.filename())[i]
                time = projectdate.split(" ")[1]
                dateContent = projectdate.split(" ")[0]
                dateContent = dateContent.split("/")[0] + "-" + dateContent.split("/")[1] + "-" + dateContent.split("/")[2]

                val dateformat = SimpleDateFormat("yyyy-MM-dd")
                val calendar = Calendar.getInstance()
                calendar.time = dateformat.parse(dateContent)
                calendar.add(Calendar.DAY_OF_MONTH, projectDuration)
                dateContent = dateformat.format(calendar.time)

                display[0] = projectname
                display[1] = projectDuration.toString()
                display[2] = "$dateContent $time"
            }
        }

        return display
    }
}

/*
        for debugging place this at the bottom of the method

        println("EF -> ${Arrays.toString(EF)}")
        println("ES -> ${Arrays.toString(ES)}")
        println("D -> ${Arrays.toString(durationarray)}")
        println("P -> ${Arrays.toString(taskarray_predecessor)}")
        println("T -> ${Arrays.toString(taskarray_element)}")
        println("S -> ${Arrays.toString(SuccessorArr)}")
        println("LF -> ${Arrays.toString(LF)}")
        println("LS -> ${Arrays.toString(LS)}")
        println("CP -> ${Arrays.toString(CriticalPath)}")
        println("project Duration -> $projectDuration")
 */