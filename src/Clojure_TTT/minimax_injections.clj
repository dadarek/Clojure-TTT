(ns clojure_ttt.minimax_injections
  (:use clojure.set)
  (:use clojure_ttt.rules)
  (:use clojure_ttt.board_utilities))


(defn wins? [square context]
  (winner? (conj (player-squares
                   (:board context)
                   (:player context))
           square))
)

(defn ties? [square context]
  (let [squares-left (#(count (filter nil? (:board context))))]
    (= 1 squares-left))
)

(defn next-context [square context]
  (let [next-player (if (= :x (:player context)) :o :x)]
    {:player next-player :board '(:x  :o :x
                                  nil :o  nil
                                  nil nil nil)}
  )
)

; TODO: Dump any utility functions that aren't being used
