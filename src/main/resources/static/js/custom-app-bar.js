const { Button, Typography, AppBar, Toolbar, Link } = MaterialUI;

/**
 * Renders a custom AppBar component that displays a navigation menu and a set
 * of buttons based on the user's authentication status.
 * 
 * @class
 * 
 * @author Alex Koh
 * 
 * @extends React.Component
 */
class CustomAppBar extends React.Component {
  /**
   * The primary color of the application.
   * 
   * @type {string}
   * 
   * @constant
   */
  primaryColor = "#5F249F";

  /**
   * The background color of the application.
   * 
   * @type {string}
   * 
   * @constant
   */
  backgroundColor = "#FFFFFF";

  /**
   * Creates a new instance of CustomAppBar.
   *
   * @constructor
   * @param {object} props - The component props.
   */
  constructor(props) {
    super(props);

    // Initialize the state of the component.
    this.state = {
      buttons: "",
    };
  }

  /**
   * Returns a logout button.
   * @returns {JSX.Element} - The logout button.
   */
  getAuthenticatedButtons() {
    return (
      <div>
        <Button href="/welcome">Welcome</Button>
        <Button href="/logout">Logout</Button>
      </div>
    )
  }

  /**
   * Returns a login and signup button.
   * @returns {JSX.Element} - The login and signup buttons.
   */
  getUnauthenticatedButtons() {
    return (
      <div>
        <Button href="/login">Login</Button>{" "}
        <Button href="/signup">Signup</Button>
      </div>
    );
  }

  /**
   * Invoked immediately after the component is mounted. Fetches the
   * authentication status of the user and sets the buttons accordingly.
   *
   * @function
   * @returns {void}
   */
  componentDidMount() {
    fetch("/api/v1/user/loggedin", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((data) => {
        return data.loggedin
          ? this.getAuthenticatedButtons()
          : this.getUnauthenticatedButtons();
      })
      .then((buttons) => {
        this.setState({ buttons: buttons });
      });
  }

  /**
   * Renders the component.
   *
   * @function
   * @returns {JSX.Element} - The component's rendered elements.
   */
  render() {
    return (
      <AppBar
        position="static"
        sx={{ backgroundColor: this.backgroundColor, color: this.primaryColor }}
      >
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            <Link href="/" sx={{ textDecoration: 'none', color: this.primaryColor }}>Login Application</Link>
          </Typography>
          {this.state.buttons}
        </Toolbar>
      </AppBar>
    );
  }
}

ReactDOM.render(<CustomAppBar />, document.getElementById("header"));
