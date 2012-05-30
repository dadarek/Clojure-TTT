(ns clojure_ttt.minimax
  (:use clojure.set))


(defn won? [squares]
  (def winning-combinations
        [#{1 2 3} #{4 5 6} #{7 8 9}
         #{1 4 7} #{2 5 8} #{3 6 9}
         #{1 5 9} #{3 5 7}])
  (some #(subset? % squares) winning-combinations))

(defn score [player square board] 1)
