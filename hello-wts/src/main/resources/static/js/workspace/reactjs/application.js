var Todo = React.createClass({
    onComplete: function() {
        this.props.complete(this.props.data.id);
    },
    render: function() {
        return (
            <li className="list-group-item">{this.props.data.text}&nbsp;&nbsp;&nbsp;&nbsp;
              <button type="button" className="btn btn-sm btn-default" onClick={this.onComplete}>
                <span className="glyphicon glyphicon-ok text-success" aria-hidden="true"></span> Done
              </button>
            </li>
        );
    }
});

var TodoList = React.createClass({
    render: function() {
        var complete = this.props.complete;
        return (
            <ul className="list-group">
                {
                    this.props.data.map(function(record) {
                        if (record.completed) {
                            return;
                        }
                        return <Todo data={record} complete={complete} key={record.id} />;
                    })
                }
            </ul>
        );
    }
});
var TodoAdditionForm = React.createClass({
    onAdd: function() {
        var text = this.refs.inputText.value;
        this.props.add(text);
        this.refs.inputText.value = null;
    },
    render: function() {
        return (
            <form>
              <div className="form-group">
                <div className="input-group">
                  <input type="text" className="form-control" ref="inputText" placeholder="Do something." />
                  <span className="input-group-btn">
                    <button type="button" className="btn btn-primary" onClick={this.onAdd}>Add</button>
                  </span>
                </div>
              </div>
            </form>
        );
    }
});

var Todos = React.createClass({
    add: function(text) {
        var seq = ++this.state.seq;
        var added = {
            seq: seq,
            data: this.state.data.concat({
                id: seq,
                text: text,
                completed: false
            })
        };
        this.setState(added);
        WebStorageSupporter.LocalStorage.setItem("state", JSON.stringify(added));
    },
    complete: function(id) {
        var seq = this.state.seq;
        var data = this.state.data;
        data.some(function(record, i){
            if (record.id==id) record.completed = true;
        });
        var completed = {
            seq: seq,
            data: data
        };
        this.setState(completed);
        WebStorageSupporter.LocalStorage.setItem("state", JSON.stringify(completed));
    },
    getInitialState: function() {
        return JSON.parse(WebStorageSupporter.LocalStorage.getItem("state")) || { seq: 0, data: [] };
    },
    render: function() {
        return (
            <section>
              <h3>Active Todos</h3>
              <TodoList data={this.state.data} complete={this.complete} />
              <div>
                <TodoAdditionForm add={this.add} />
              </div>
            </section>
        );
    }
});

var SamplePage = React.createClass({
    clear: function() {
        WebStorageSupporter.LocalStorage.clear();
        location.reload();
    },
    render: function() {
        return (
            <section>
              <h2>Sample Page <small>サンプルページ</small></h2>
              <Todos />
              <p>
                <button type="button" className="btn btn-danger" onClick={this.clear}>初期状態に戻す</button>
              </p>
            </section>
        );
    }
});

ReactDOM.render(
    <SamplePage />,
    document.getElementById('sample')
);
