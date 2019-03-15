
from com.cm.util.singletone_tool import Singleton
from com.cm.main.main_frame import MainWindow
from com.cm.util.gameEnums import SceneNumber
from com.cm.scene.scene_factory import sceneFactory

class LifeCycleController(metaclass=Singleton):

    def setSceneToMainFrame(self, scenenum):
        factory = sceneFactory()

        if scenenum == SceneNumber.MainMenuScene:
            MainWindow().setScene(factory.makeMainMenuScene())
        elif scenenum == SceneNumber.IntroScene:
            pass

