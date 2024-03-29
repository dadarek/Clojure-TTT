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
  (do
    (let [current-squares (player-squares board player)
          new-squares (conj current-squares square)
          new-board (take-square square player board)
          opponent (if (= :x player) :o :x)
          empty-squares (get-empty-squares new-board)
          opponents-score (fn [square] (score opponent square new-board))
          opponents-best (fn [] (reduce max (map opponents-score empty-squares)))]
      (cond
        (won? new-squares) 1
        (full? new-board) 0
        :else (- (opponents-best)))))
  )

(defn next-move [player board]
  (if (is-empty? board)
    1
    (let [empty-squares (get-empty-squares board)
          their-scores (map #(score player % board) empty-squares)
          top-score (reduce max their-scores)
          top-score-index (.indexOf their-scores top-score)]
      (nth (apply list empty-squares) top-score-index))))

