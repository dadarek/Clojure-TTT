(ns clojure_ttt.minimax-spec
  (:use [clojure_ttt.minimax])
  (:use [speclj.core]))

(describe "Minimax"
  (it "Scores full board as 0's"
    (let [expected-scores
            '(0  0  0
              0  0  0
              0  0  0)
          board
            '(:x :o :x
              :x :o :o
              :o :x :x)]
      (should= expected-scores (value-each-square board))))

  (it "Scores winning square as 1"
    (let [expected-scores
            '(0  0  0
              0  0  0
              0  0  1)
          board
            '(:x :o :x
              :x :x :o
              :o :o nil)]
      (should= expected-scores (value-each-square board))))

  (it "Scores losing square as 1"
    (let [expected-scores
            '(0  0  0
              0  0  0
              0  0  0)
          board
            '( :x :o :x
              nil :x :o
               :o :x :o)]
      (should= expected-scores (value-each-square board))))
)

(run-specs)

