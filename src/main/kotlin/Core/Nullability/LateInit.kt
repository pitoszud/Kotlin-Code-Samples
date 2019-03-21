package Core.Nullability



class Service{
    fun action(): String = "action name"
}

class ServiceTest{
    private lateinit var serv1: Service
    private var serv2: Service? = null

    fun setUp(){
        serv1 = Service()
        if (serv2 != null) serv2 = Service()
    }

    fun testAction(){
        //Assert.assertEquals("action name", service.action())
    }
}
