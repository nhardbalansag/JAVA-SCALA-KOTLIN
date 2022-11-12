package KotlinClass
import KotlinHelpers.Helpers
import java.io.File

class User {

    val helper = Helpers()
    val fileName: String = "src/Files/user"

    fun create(id: Int, firstname: String, lastname: String, username: String, password: String, createdAt: String): Boolean{

        var dataContent: String
        var dataReturn: Boolean = false
        if(
            !helper.checknullInput(id.toString()) ||
            !helper.checknullInput(firstname) ||
            !helper.checknullInput(lastname) ||
            !helper.checknullInput(username) ||
            !helper.checknullInput(password) ||
            !helper.checknullInput(createdAt)){
            dataContent = "$id, $firstname, $lastname, $username, $password, $createdAt"
            dataReturn = this.helper.checkFileExist(this.fileName, dataContent)
        }

        return dataReturn
    }

    fun readOne(username: String, password: String): Boolean{
        var dataBool: Boolean = false
        if(!username.isEmpty() && !password.isEmpty()){
            if(!this.helper.readFile(this.fileName, username).isEmpty() && !this.helper.readFile(this.fileName, password).isEmpty()){
                dataBool = true
            }
        }
        return dataBool
    }
}