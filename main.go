type processLock struct {
	mtx      sync.Mutex
	refcount int
}

type locker struct {
	mtx   sync.Mutex
	locks map[string]*processLock
}

func (l *locker) acquire(key string) {
	l.mtx.Lock()
	lk, found := l.locks[key]
	if !found {
		lk = &processLock{}
		l.locks[key] = lk
	}
	lk.refcount++
	l.mtx.Unlock()
	lk.mtx.Lock()
}

func (l *locker) release(key string) {
	l.mtx.Lock()
	lk := l.locks[key]
	lk.refcount--
	if lk.refcount == 0 {
		delete(l.locks, key)
	}
	l.mtx.Unlock()
	lk.mtx.Unlock()
}