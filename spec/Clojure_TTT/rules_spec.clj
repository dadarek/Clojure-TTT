(ns clojure_ttt.rules-spec
  (:use [clojure_ttt.rules])
  (:use [speclj.core]))

(describe "Rules"
  (it "recognizes the 8 winning lines"
    (should (winner? #{1 2 3}))
    (should (winner? #{4 5 6}))
    (should (winner? #{7 8 9}))
    (should (winner? #{1 4 7}))
    (should (winner? #{2 5 8}))
    (should (winner? #{3 6 9}))
    (should (winner? #{1 5 9}))
    (should (winner? #{3 5 7})))

  (it "ignores their ordering"
    (should (winner? #{2 3 1})))

  (it "ignores extraneous squares"
    (should (winner? #{1 2 3 4}))
    (should (winner? #{3 4 5 6})))

  (it "doesn't simply return true for everything"
    (should-not (winner? #{}))
    (should-not (winner? #{3}))
    (should-not (winner? #{8 9}))
    (should-not (winner? #{3 8 9}))
    (should-not (winner? #{1 3 5 8}))))

(run-specs)
