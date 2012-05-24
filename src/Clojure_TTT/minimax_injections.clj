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

(defn sum-of-children [square context]
  (let [next-player (#(if (= :x (:player context)) :o :x))
        empty-squares (get-empty-squares (:board context))
        new-board (#(take-square square context))
        new-context {:player next-player
                     :board new-board} ]
  (reduce + (map #(sum-of-children % new-context) empty-squares)))
  ; this has to get injected back into minimax
)
