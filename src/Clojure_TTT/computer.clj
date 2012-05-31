(ns clojure_ttt.computer
  (:require clojure_ttt.minimax)
  (:use clojure_ttt.runner))

(defrecord Computer []
  Player
  (next-move [_ x-or-o board]
    (clojure_ttt.minimax/next-move x-or-o board)))

