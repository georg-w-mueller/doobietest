import doobietest.dt.F

@main
def hello: Unit =
  val r = F.res;

  val rc = F.resc

  println(r.get)

  println(rc.get)

  println(F.clstring)

  println("Done")
