package KotlinConstructor

class TeamConstructor {

    var id : String = ""
    var teamname : String = ""
    var teamstatus : String = ""
    var createdAt : String = ""

    fun setid(data: String){
        this.id = data
    }

    fun getid(): String{
        return this.id
    }

    fun setteamname(data: String){
        this.teamname = data
    }

    fun getteamname(): String{
        return this.teamname
    }

    fun setteamstatus(data: String){
        this.teamstatus = data
    }

    fun getteamstatus(): String{
        return this.teamstatus
    }

    fun setcreatedAt(data: String){
        this.createdAt = data
    }

    fun getcreatedAt(): String{
        return this.createdAt
    }

}