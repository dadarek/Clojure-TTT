(ns clojure_ttt.human_player
  (:use clojure_ttt.runner))

(defprotocol NextMoveUI
  (get-next-move [ui]))

(defrecord HumanPlayer [ui]
  Player
  (next-move [player _ board] (get-next-move ui)))

