package KotlinClass

import KotlinHelpers.Helpers

class Team {

    val helper = Helpers()
    var fileName: String = "src/Files/team"

    fun create(id: String, name: String, status: String, createdat: String): Boolean{
        var dataContent: String
        var dataReturn: Boolean = false
        if(!helper.checknullInput(id) && !helper.checknullInput(name) && !helper.checknullInput(status) && !helper.checknullInput(createdat)){
            dataContent = "$id,$name,$status,$createdat"
            dataReturn = this.helper.checkFileExist(this.fileName, dataContent)
        }

        return dataReturn
    }

    fun filename(): String{
        return this.fileName
    }
}