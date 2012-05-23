(ns clojure_ttt.minimax)

(defn value-square [square board]
  (if (= nil square) 1 0))

(defn value-each-square [board]
  (for [square board]
    (value-square square board)
    )
)
