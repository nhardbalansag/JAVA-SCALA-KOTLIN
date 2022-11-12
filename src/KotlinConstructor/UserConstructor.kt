package KotlinConstructor

class UserConstructor {

    var id: Int = 0
    var firstname: String = ""
    var lastname: String = ""
    var username: String = ""
    var password: String = ""
    var createdAt: String = ""

    fun setid(data: Int){
        this.id = data
    }

    fun getid(): Int{
        return this.id
    }

    fun setfirstname(data: String){
        this.firstname = data
    }

    fun getfirstname(): String{
        return this.firstname
    }

    fun setlastname(data: String){
        this.lastname = data
    }

    fun getlastname(): String{
        return this.lastname
    }

    fun setemail(data: String){
        this.username = data
    }

    fun getemail(): String{
        return this.username
    }

    fun setpassword(data: String){
        this.password = data
    }

    fun getpassword(): String{
        return this.password
    }

    fun setcreatedAt(data: String){
        this.createdAt = data
    }

    fun getcreatedAt(): String{
        return this.createdAt
    }

}