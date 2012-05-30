(ns clojure_ttt.core
  (:use clojure_ttt.runner)
  (:use clojure_ttt.human_player)
  (:import [clojure_ttt.human_player HumanPlayer])
  (:use clojure_ttt.computer)
  (:import [clojure_ttt.computer Computer])
  (:use clojure_ttt.console_ui)
  (:import [clojure_ttt.console_ui ConsoleUI])
)

(def ui (ConsoleUI.))
(def human (HumanPlayer. ui))
(def computer (Computer. :x))


(defn -main []
  (run human computer ui)
)
