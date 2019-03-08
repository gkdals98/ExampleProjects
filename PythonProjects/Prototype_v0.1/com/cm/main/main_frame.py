from PyQt5 import QtWidgets


class MainWindow(QtWidgets.QMainWindow):
    def __init__(self, parent=None):
        super(MainWindow, self).__init__(parent)
        self.initUI()

    def initUI(self):
        self.setWindowTitle('Prototype_v0.1')
        self.setGeometry(300, 300, 500, 400)
        self.show()
