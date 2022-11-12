package KotlinClass

import KotlinHelpers.Helpers
import java.io.File

class Task {

    val helper = Helpers()
    var fileName: String = "src/Files/task"

    fun create(
        id: String,
        project: String,
        taskname: String,
        team: String,
        status: String,
        createdAt: String,
        duration: String
    ): Boolean{
        var dataContent: String
        var dataReturn: Boolean = false
        if(
            !helper.checknullInput(id) &&
            !helper.checknullInput(project) &&
            !helper.checknullInput(taskname) &&
            !helper.checknullInput(team) &&
            !helper.checknullInput(status) &&
            !helper.checknullInput(createdAt) &&
            !helper.checknullInput(duration)){

            if(helper.checkLessThanZeroandIfNumber(duration)){
                dataContent = "id-$id,$project,$taskname,$team,$status,$createdAt,$duration,none"
                dataReturn = this.helper.checkFileExist(this.fileName, dataContent)
            }
        }

        return dataReturn
    }

    fun insertSuccessor(id: String, taskname: String): Boolean{
        var dataContent: String
        var dataReturn: Boolean = false
        if(
            !helper.checknullInput(id) &&
            !helper.checknullInput(taskname)
           ){
            dataReturn = helper.editline(this.filename(), id, taskname)
        }
        return dataReturn
    }

    fun filename(): String{
        return this.fileName
    }

    fun showTaskRelatedTask(projectId : String, Col: Int): Array<String>{
        var size = helper.countRelatedFile(filename(), projectId, 1)
        var dataReturnValue = Array(size) {""}
        var inc = 0

        helper.readAll(1, filename()).forEachIndexed{ i, data_i ->
            if (projectId == data_i){
                var data = helper.readAll(Col, filename())[i]
                dataReturnValue[inc] = data
                inc++
            }
        }
        return dataReturnValue
    }

    fun EditDuration(index: String, newData : String): Boolean{
        var result = false
        if(helper.checkLessThanZeroandIfNumber(newData)){
            result = helper.editCol(filename(), index, newData, 6)
        }else{
            result = false
        }
        return result
    }
}