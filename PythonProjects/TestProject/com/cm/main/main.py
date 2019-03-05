from functools import partial
from PyQt5 import QtCore, QtWidgets

class CardWidget(QtWidgets.QWidget):
    def __init__(self, parent=None):
        super(CardWidget, self).__init__(parent)
        self._layout = QtWidgets.QStackedLayout(self)
        button_widget = QtWidgets.QWidget()
        self.btn_lay = QtWidgets.QFormLayout(button_widget)

        self._layout.addWidget(button_widget)

    def add_widget(self, text, widget):
        self._layout.addWidget(widget)
        btn = QtWidgets.QPushButton(text)
        self.btn_lay.addRow(btn)
        btn.clicked.connect(partial(self._layout.setCurrentWidget, widget))

    @QtCore.pyqtSlot()
    def back(self):
        self._layout.setCurrentIndex(0)


class Widget(QtWidgets.QWidget):
    backSignal = QtCore.pyqtSignal()

    def __init__(self, parent=None):
        super(Widget, self).__init__(parent)

        self.le1 = QtWidgets.QLineEdit()
        self.le2 = QtWidgets.QLineEdit()

        button = QtWidgets.QPushButton("Change")
        button.clicked.connect(self.backSignal)

        flay = QtWidgets.QFormLayout()
        flay.addRow("Value 1:", self.le1)
        flay.addRow("Value 2:", self.le2)

        lay = QtWidgets.QVBoxLayout(self)
        lay.addLayout(flay)
        lay.addWidget(button)


def create_label():
    label = QtWidgets.QLabel(
        "Some Other Components",
        alignment=QtCore.Qt.AlignCenter
    )
    label.setStyleSheet("background-color:blue;")
    return label


if __name__ == '__main__':
    import sys
    app = QtWidgets.QApplication(sys.argv)

    c = CardWidget()
    for i in range(3):
        w = Widget()
        w.backSignal.connect(c.back)
        c.add_widget("Want to Change value {}".format(i+1), w)

    p = QtWidgets.QWidget()
    lay = QtWidgets.QGridLayout(p)
    lay.addWidget(create_label(), 0, 0, 1, 2)
    lay.addWidget(c, 1, 0)
    lay.addWidget(create_label(), 1, 1)
    lay.setColumnStretch(0, 1)
    lay.setColumnStretch(1, 1)
    lay.setRowStretch(0, 1)
    lay.setRowStretch(1, 1)
    p.resize(640, 480)
    p.show()
    sys.exit(app.exec_())