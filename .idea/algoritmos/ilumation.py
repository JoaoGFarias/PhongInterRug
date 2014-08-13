__author__ = 'Joao'

from objects import Point
from objects import Camera
from objects import Vector
from objects import Color

class Ilumination:

    def __init__(self,lightPoint,kA,iA,kD,oD,kS,iL,rugosity):
        assert isinstance(lightPoint,Vector)
        assert isinstance(iA,Vector)
        assert isinstance(iL,Vector)
        assert isinstance(kA,float)
        assert isinstance(kD,float)
        assert isinstance(kS,float)
        assert isinstance(oD,float)
        assert isinstance(rugosity,float)

        self.lightPoint = lightPoint
        self.ka = kA
        self.iA = iA * kA
        self.kD = kD
        self.oD = oD
        self.kS = kS
        self.iL = iL
        self.rugosity = rugosity
        pass

    def ilumination(self,point,camera):

        assert isinstance(point, Point)
        assert isinstance(camera, Camera)

        error = 0.001

        # I = Ia + Ie + Id
        # Ia = Ia * Ka
        # Ie = <V,R>^n * Ks * Il
        # Id = <L, N> * Kd * Od * Il

        v = point.createV().normalize()
        l = point.createL(self.lightPoint).normalize()
        n = point.createN().normalize()

        #Inverting direction of normal, if <V,N> < 0 (with float-point error)
        if Vector.dotProduct(v, n) <= error:
            n = ~n

        r = ((n * (2 * Vector.dotProduct(l, n))) - l).normalize()

        #If the light is behind the triangle, the difuse and specular components
        #are null

        if Vector.dotProduct(l, n) >= error:
            iD = (self.oD * self.iL) * (Vector.dotProduct(l, n) * self.kD)

            if Vector.dotProduct(v, r) >= error:
                iE = self.iL * ((Vector.dotProduct(v, r) ** self.rugosity) * self.kS)
            else:
                iE = Vector()
        else:
            iD = Vector()
            iE = Vector()

        return Color(self.iA + iE + iD)




