(ns clojure_ttt.human_player
  (:use clojure_ttt.runner))

(defrecord HumanPlayer [ui]
  Player
  (next-move [player board] 5))

