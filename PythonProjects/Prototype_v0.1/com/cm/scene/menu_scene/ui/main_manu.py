from PyQt5 import QtWidgets

class MainMenu(QtWidgets.QWidget):
    def __init__(self, parent=None):
        super(MainMenu, self).__init__(parent)
        self.initUI()

    def initUI(self):
        self.startButton = QtWidgets.QPushButton('Start')

