(ns clojure_ttt.ui_player
  (:use clojure_ttt.runner))

(defrecord UIPlayer [x-or-o]
  Player
  (next-move [player board] 5))

