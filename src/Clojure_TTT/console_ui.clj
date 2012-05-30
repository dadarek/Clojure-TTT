(ns clojure_ttt.console_ui
  (:use clojure_ttt.human_player))

(defrecord ConsoleUI []
  NextMoveUI
  (get-next-move [this] 5))

