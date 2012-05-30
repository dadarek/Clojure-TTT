(ns clojure_ttt.minimax-spec
  (:use clojure_ttt.minimax)
  (:use [speclj.core]))

(describe "Minimax"
  (it "Knows all winning combinations"
    (should (won? #{1 2 3}))
    (should (won? #{4 5 6}))
    (should (won? #{7 8 9}))
    (should (won? #{1 4 7}))
    (should (won? #{2 5 8}))
    (should (won? #{3 6 9}))
    (should (won? #{1 5 9}))
    (should (won? #{3 5 7})))

  (it "Knows some losing combinations"
    (should-not (won? #{2 3}))
    (should-not (won? #{1 5 6}))
    (should-not (won? #{7 8}))
    (should-not (won? #{9}))
    (should-not (won? #{3 7}))))

(run-specs)

