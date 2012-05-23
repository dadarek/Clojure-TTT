(ns clojure_ttt.rules-spec
  (:use [clojure_ttt.rules])
  (:use [speclj.core]))

(describe "Rules"
  (it "should treat [1 2 3] as winner"
    (should (winner? #{1 2 3}))
    (should (winner? #{2 3 1}))
      )

  (it "should NOT treat [2 3 4] as winner"
    (should-not (winner? #{2 3 4})))

  (it "should NOT treat [3 4 5] as winner"
    (should-not (winner? #{3 4 5})))

  (it "should treat [4 5 6] as winner"
    (should (winner? #{4 5 6})))

  (it "should treat [1 2 3 4] as winner"
    (should (winner? #{1 2 3 4})))
)

(run-specs)
