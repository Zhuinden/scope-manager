package com.zhuinden.scopemanager;

import com.zhuinden.scopemanager.testkeys.A;
import com.zhuinden.scopemanager.testkeys.B;
import com.zhuinden.scopemanager.testkeys.C;
import com.zhuinden.scopemanager.testkeys.D;
import com.zhuinden.scopemanager.testkeys.E;
import com.zhuinden.scopemanager.testkeys.F;
import com.zhuinden.scopemanager.testkeys.G;
import com.zhuinden.scopemanager.testkeys.H;
import com.zhuinden.scopemanager.testkeys.I;
import com.zhuinden.scopemanager.testkeys.J;
import com.zhuinden.scopemanager.testkeys.K;
import com.zhuinden.scopemanager.testkeys.L;
import com.zhuinden.scopemanager.testkeys.M;
import com.zhuinden.scopemanager.testkeys.N;
import com.zhuinden.scopemanager.testkeys.O;
import com.zhuinden.scopemanager.testkeys.P;
import com.zhuinden.scopemanager.testkeys.Q;
import com.zhuinden.scopemanager.testkeys.R;
import com.zhuinden.servicetree.ServiceTree;
import com.zhuinden.simplestack.Backstack;
import com.zhuinden.simplestack.StateChange;
import com.zhuinden.simplestack.StateChanger;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Zhuinden on 2017.03.01..
 */

public class ScopeManagerTest {
    /*
                A   -   B   -   C
                |       |       |
                D       I       K
             /  |  \    |    /  |  \
            E   F   G   J   L   M  N
                |             / | \
                H            O  P  Q
                                |
                                R
    
     */

    A a;
    B b;
    C c;
    D d;
    E e;
    F f;
    G g;
    H h;
    I i;
    J j;
    K k;
    L l;
    M m;
    N n;
    O o;
    P p;
    Q q;
    R r;

    ScopeManager scopeManager;
    StateChanger stateChanger = new StateChanger() {
        @Override
        public void handleStateChange(StateChange stateChange, Callback completionCallback) {
            scopeManager.setupServices(stateChange);
            completionCallback.stateChangeComplete();
        }
    };

    @Before
    public void setup() {
        a = new A();
        b = new B();
        c = new C();
        d = new D(a);
        e = new E(d);
        f = new F(d);
        g = new G(d);
        h = new H(f);
        i = new I(b);
        j = new J(i);
        k = new K(c);
        l = new L(k);
        m = new M(k);
        n = new N(k);
        o = new O(m);
        p = new P(m);
        q = new Q(m);
        r = new R(q);

        scopeManager = new ScopeManager(null);
    }


    @Test
    public void testConstructA() {
        Backstack backstack = new Backstack(a);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(a)).isTrue();
    }

    @Test
    public void testConstructB() {
        Backstack backstack = new Backstack(b);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(b)).isTrue();
    }

    @Test
    public void testConstructC() {
        Backstack backstack = new Backstack(c);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(c)).isTrue();
    }

    @Test
    public void testConstructD() {
        Backstack backstack = new Backstack(d);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(d)).isTrue();
    }

    @Test
    public void testConstructE() {
        Backstack backstack = new Backstack(e);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(e)).isTrue();
    }

    @Test
    public void testConstructF() {
        Backstack backstack = new Backstack(f);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(f)).isTrue();
    }

    @Test
    public void testConstructG() {
        Backstack backstack = new Backstack(g);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(g)).isTrue();
    }

    @Test
    public void testConstructH() {
        Backstack backstack = new Backstack(h);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(h)).isTrue();
    }

    @Test
    public void testConstructI() {
        Backstack backstack = new Backstack(i);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(i)).isTrue();
    }

    @Test
    public void testConstructJ() {
        Backstack backstack = new Backstack(j);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(j)).isTrue();
    }

    @Test
    public void testConstructK() {
        Backstack backstack = new Backstack(k);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(k)).isTrue();
    }

    @Test
    public void testConstructL() {
        Backstack backstack = new Backstack(l);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(l)).isTrue();
    }

    @Test
    public void testConstructM() {
        Backstack backstack = new Backstack(m);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(m)).isTrue();
    }

    @Test
    public void testConstructN() {
        Backstack backstack = new Backstack(n);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(n)).isTrue();
    }

    @Test
    public void testConstructO() {
        Backstack backstack = new Backstack(o);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(o)).isTrue();
    }

    @Test
    public void testConstructP() {
        Backstack backstack = new Backstack(p);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(p)).isTrue();
    }

    @Test
    public void testConstructQ() {
        Backstack backstack = new Backstack(q);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(q)).isTrue();
    }

    @Test
    public void testConstructR() {
        Backstack backstack = new Backstack(r);
        backstack.setStateChanger(stateChanger, Backstack.INITIALIZE);
        ServiceTree serviceTree = scopeManager.getServiceTree();
        assertThat(serviceTree.hasNodeWithKey(r)).isTrue();
    }
}
