class MyCounter extends HTMLElement {
    constructor () {
    super();
    this.shadow = this.attachShadow({mode: "open" });
    }

    get count() {
        return this.getAttribute("test")

    }

    set count(val) {
        this.setAttribute("test", val);
    }

    static get observedAttributes() {
        return["test"];
    }

    attributeChangedCallback(prop, oldVal, newVal) {
        if(prop === "test") this.render();
    }


    connectedCallback() {
        this.render();
    }

    render() {
        this.shadow.innerHTML =`
        <h1>Vanilla HTML</h1> 
        ${this.count}
        
        <button id='btn'>Increment</button>
        `;
    }
}

customElements.define('my-counter', MyCounter);