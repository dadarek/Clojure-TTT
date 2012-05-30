(ns clojure_ttt.computer
  (:use clojure_ttt.runner))

(defrecord Computer [x-or-o]
  Player
  (next-move [player board] 2))

