(ns clojure_ttt.minimax-spec
  (:use [clojure_ttt.minimax])
  (:use [speclj.core]))

(describe "Minimax"
  (it "Scores full board as 0's"
    (let [board           '(:x :o :x
                            :x :o :o
                            :o :x :x)
          expected-values '(0  0  0
                            0  0  0
                            0  0  0)]
      (should=
        expected-values
        (value-each-square board))))

  (it "Scores winning square as 1"
    (let [board           '(:x :o :x
                            :x :x :o
                            :o :o nil)
          expected-values '(0  0  0
                            0  0  0
                            0  0  1)]
      (should=
        expected-values
        (value-each-square board))))

  (it "Scores tie square as 0"
    (let [board         '( :x :o :x
                          nil :x :o
                           :o :x :o)
          expected-values '(0  0  0
                            0  0  0
                            0  0  0)]
      (should=
        expected-values
        (value-each-square board))))
)

(run-specs)

