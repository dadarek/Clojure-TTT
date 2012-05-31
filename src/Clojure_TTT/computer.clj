(ns clojure_ttt.computer
  (:require clojure_ttt.minimax)
  (:use clojure_ttt.runner))

(defrecord Computer [x-or-o]
  Player
  (next-move [_ board] 
    (clojure_ttt.minimax/next-move x-or-o board)))

