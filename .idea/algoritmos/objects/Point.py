__author__ = 'Joao'

import Vector
import copy.deepcopy

class Point:
    pass


class Point3D(Point):
    def __init__(self):
        self.x = 0
        self.y = 0
        self.z = 0
        self.normal = Vector()
        pass

    def __init__(self, x, y, z):
        assert isinstance(x,float)
        assert isinstance(y,float)
        assert isinstance(z,float)
        self.x = x
        self.y = y
        self.z = z
        self.normal = Vector()
        pass

    def createV(self):
        return Vector(self.x*-1,self.y*-1,self.z*-1)
        pass

    def createL(self,l):
        return Vector(self,l)
        pass

    @property
    def createN(self):
        return copy.deepcopy(self.normal)
        pass
