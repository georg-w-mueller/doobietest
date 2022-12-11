import doobietest.dt.F

@main
def hello: Unit =
  var r = F.res;

  println(r.get)
  println("Done")
