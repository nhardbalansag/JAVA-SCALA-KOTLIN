package KotlinConstructor

class ProjectConstructor {

    var id : String = ""
    var projectname : String = ""
    var status : String = ""
    var createdAt : String = ""

    fun setid(data: String){
        this.id = data
    }

    fun getid(): String{
        return this.id
    }

    fun setprojectname(data: String){
        this.projectname = data
    }

    fun getprojectname(): String{
        return this.projectname
    }

    fun setstatus(data: String){
        this.status = data
    }

    fun getstatus(): String{
        return this.status
    }

    fun setcreatedAt(data: String){
        this.createdAt = data
    }

    fun getcreatedAt(): String{
        return this.createdAt
    }

}