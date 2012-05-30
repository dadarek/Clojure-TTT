(ns clojure_ttt.minimax
  (:use clojure_ttt.board_utilities)
  (:use clojure.set))


(defn won? [squares]
  (def winning-combinations
        [#{1 2 3} #{4 5 6} #{7 8 9}
         #{1 4 7} #{2 5 8} #{3 6 9}
         #{1 5 9} #{3 5 7}])
  (some #(subset? % squares) winning-combinations))

(defn score [player square board]
  (def new-squares (conj (player-squares board player) square))
  (def new-board (take-square square {:board board :player player}))
  (def opponent (if (= :x player) :o :x))

  (cond
    (won? new-squares) 1
    (full? new-board) 0
    :else -1)
)
