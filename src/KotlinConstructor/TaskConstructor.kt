package KotlinConstructor

class TaskConstructor {

    var id : String = ""
    var taskname : String = ""
    var projectname : String = ""
    var taskStatus : String = ""
    var taskDuration : String = ""
    var taskTeam : String = ""
    var createdAt : String = ""
    var Successortask : String = ""
    var Progress : String = ""

    fun setid(data: String){
        this.id = data
    }

    fun getid(): String{
        return this.id
    }

    fun setproject_name(data: String){
        this.projectname = data
    }

    fun getproject_name(): String{
        return this.projectname
    }

    fun settaskname(data: String){
        this.taskname = data
    }

    fun gettaskname(): String{
        return this.taskname
    }

    fun settaskStatus(data: String){
        this.taskStatus = data
    }

    fun gettaskStatus(): String{
        return this.taskStatus
    }

    fun settaskDuration(data: String){
        this.taskDuration = data
    }

    fun gettaskDuration(): String{
        return this.taskDuration
    }

    fun setcreatedAt(data: String){
        this.createdAt = data
    }

    fun getcreatedAt(): String{
        return this.createdAt
    }

    fun settaskTeam(data: String){
        this.taskTeam = data
    }

    fun gettaskTeam(): String{
        return this.taskTeam
    }

    fun setSuccesor_task(data: String){
        this.Successortask = data
    }

    fun getSuccesor_task(): String{
        return this.Successortask
    }

    fun setProgress_task(data: String){
        this.Progress = data
    }

    fun getProgress_task(): String{
        return this.Progress
    }
}