package KotlinClass

import KotlinHelpers.Helpers

class Project {

    val helper = Helpers()
    var fileName: String = "src/Files/project"

    fun create(id: String, name: String, status: String, createdat: String): Boolean{
        var dataContent: String
        var dataReturn: Boolean = false
        if(!helper.checknullInput(id) && !helper.checknullInput(name) && !helper.checknullInput(status) && !helper.checknullInput(createdat)){
            dataContent = "p-id-$id,$name,$status,$createdat"
            dataReturn = this.helper.checkFileExist(this.fileName, dataContent)
        }

        return dataReturn
    }

    fun filename(): String{
        return this.fileName
    }

}
