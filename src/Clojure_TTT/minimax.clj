(ns clojure_ttt.minimax)

(defn value-square [player square board]
  (if (= nil square) 1 0))

(defn value-each-square [player, board]
  (for [square board]
    (value-square player square board)
    )
)
