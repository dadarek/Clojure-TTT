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
    (should (winner? #{3 5 7}))
  )

  (it "ignores order"
    (should (winner? #{2 3 1})))

  (it "should NOT treat [3 4 5] as winner"
    (should-not (winner? #{3 4 5})))

  (it "should treat [4 5 6] as winner"
    (should (winner? #{4 5 6})))

  (it "should treat [1 2 3 4] as winner"
    (should (winner? #{1 2 3 4})))
)

(run-specs)
