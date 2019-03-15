from PyQt5 import QtWidgets
from com.cm.util.singletone_tool import Singleton

class MainWindow(metaclass=Singleton):
    def __init__(self):
        self.main_ui = QtWidgets.QMainWindow()

    def initUI(self):
        self.main_ui.setWindowTitle('Prototype_v0.1')
        self.main_ui.setGeometry(300, 300, 500, 400)

    def showFrame(self):
        self.main_ui.show()

    def setScene(self, scene):
        self.main_ui.setCentralWidget(scene)
        self.update()

    def update(self):
        self.main_ui.update()