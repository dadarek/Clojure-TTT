(ns clojure_ttt.computer
  (:use clojure_ttt.generic_minimax)
  (:use clojure_ttt.runner))

(defrecord Computer [x-or-o]
  Player
  (next-move [player board]
    (let [result (score nil nil nil nil nil)
          top (reduce max result)]
      (inc (.indexOf result top))
    )))

