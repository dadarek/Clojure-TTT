(ns clojure_ttt.minimax_injections
  (:use clojure.set)
  (:use clojure_ttt.rules))


(defn player-squares [board player]
  (set
    (filter #(not= nil %)
      (map (fn [i] (if (= player (nth board i)) (inc i))) (range 0 9))
    )
  )
)

(defn wins? [square context]
  (winner? (conj (player-squares
                   (:board context)
                   (:player context))
           square))
)
