const {
  Button,
  Container,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Typography,
} = MaterialUI;

/**
 * The Restricted component displays a restricted page that can only be accessed
 * by authenticated users. It fetches a list of users from the server and
 * displays them in a table. If the current user is a manager, they can promote
 * other users to become managers as well.
 * 
 * @class
 * 
 * @author Alex Koh
 * 
 * @extends React.Component
 */
class Restricted extends React.Component {
  /**
   * The primary color of the application.
   * 
   * @type {string}
   * 
   * @constant
   */
  primaryColor = "#5F249F";

  /**
   * Constructs the Restricted component.
   * 
   * @param {Object} props - The props object passed to the component. 
   */
  constructor(props) {
    super(props);

    /**
     * The state object that contains the list of users.
     * 
     * @type {Object}
     * 
     * @property {Array} users - The list of users. Defaults to an empty array.
     */
    this.state = {
      users: [],
    };
  }

  /**
   * Invoked immediately after the component is mounted. Fetches the list of
   * users from the server.
   *
   * @function
   * @returns {void}
   */
  componentDidMount() {
    fetch("/api/v1/all-users", {
      method: "GET",
    })
      .then((response) => response.json())
      .then((data) => {
        this.setState({ users: data });
      });
  }

  /**
   * Renders the component.
   *
   * @function
   * @returns {JSX.Element}
   */
  render() {
    return (
      <Container maxWidth="md" sx={{ marginTop: 4 }}>
        <Typography component="h1" variant="h5">
          Restricted Page!
        </Typography>
        <TableContainer component={Paper} elevation="3" sx={{ marginTop: 4 }}>
          <Table aria-label="user information">
            <TableHead>
              <TableRow>
                <TableCell sx={{ color: this.primaryColor }}>ID</TableCell>
                <TableCell sx={{ color: this.primaryColor }}>Username</TableCell>
                <TableCell sx={{ color: this.primaryColor }}>Name</TableCell>
                <TableCell sx={{ color: this.primaryColor }}>Is Manager</TableCell>
                <TableCell sx={{ color: this.primaryColor }}>Actions</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {this.state.users.map((user) => (
                <TableRow key={user.id}>
                  <TableCell component="th" scope="row">
                    {user.id}
                  </TableCell>
                  <TableCell>{user.username}</TableCell>
                  <TableCell>{user.name}</TableCell>
                  <TableCell>{user.manager ? "Yes" : "No"}</TableCell>
                  <TableCell>
                    {!user.manager && (
                      <Button
                        onClick={() => {
                          fetch("/api/v1/users", {
                            method: "PUT",
                            headers: {
                              "Content-Type": "application/json",
                            },
                            body: JSON.stringify({
                              username: user.username,
                              field: "isManager",
                            }),
                          })
                            .then((response) => response.json())
                            .then((data) => {
                              if (data.success) {
                                window.location.reload();
                              }
                            });
                        }}
                      >
                        Make Manager
                      </Button>
                    )}
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Container>
    );
  }
}

ReactDOM.render(<Restricted />, document.getElementById("root"));
