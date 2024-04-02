import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {toast} from 'react-hot-toast';
import axios from "axios";
import "./login.css";
import {LoginType} from "../../model/login.model";
import {useForm} from "react-hook-form";
import {getUriUser} from "../../UrlTools";
import { jwtDecode } from 'jwt-decode';
import {User} from "../../model/user.model";
import {toaster} from "evergreen-ui";

function Login  ()   {
    const navigate = useNavigate();
    const [userForm, setForm] = useState<LoginType>({
        username: "",
        password: "",
    });

    useEffect(() => {
        if (localStorage.getItem('token') !== "" && localStorage.getItem('token') !== null){
            navigate('/reserved-room');
            toaster.success("NOTIFICATION", {
                description: "Vous êtes déjà connecté!"
            });
        }
    })

    const getUser = (username: string) => {
        axios.get(getUriUser(`getbyusername/${username}`)).then((response) => {
            if (response.hasOwnProperty("data")) {
                console.log(response.data.object);
                localStorage.setItem('user', JSON.stringify(response.data.object));
            }
        }).catch(error => {
        })
    }

    const {handleSubmit} = useForm();

    function onSubmit(data: any): any{
        axios.post(getUriUser(`login`), userForm).then((response) => {
            if (response.data.status===0){
                const username: string = jwtDecode(response.data.accessToken).sub!;
                localStorage.setItem('token', response.data.accessToken);
                getUser(username);
                console.log(response.data.accessToken);
                // Mise à jour de l'état avec les données décodées
                toast.success("Vous êtes connecté!");
                navigate('/reserved-room');
            }else {
                toast.error("Username ou password incorrect!");
            }
        })
    }

    const registerUser = () => {
        navigate('/register')
    }

    return (
        <div>
            {/* TopBar Component */}
            <div className="login">
                <form onSubmit={handleSubmit(onSubmit)}>
                    <div className="lContainer">
                        <input
                            type="text"
                            placeholder="Email"
                            id="username"
                            value={userForm.username}
                            name={"username"}
                            onChange={(e: any) => {
                                setForm(prevState => ({
                                    ...prevState,
                                    username: e.target.value,
                                }));
                            }}
                            className="lInput"
                        />
                        <input
                            type="password"
                            placeholder="Password"
                            id="password"
                            value={userForm.password}
                            name={"password"}
                            onChange={(e: any) => {
                                setForm(prevState => ({
                                    ...prevState,
                                    password: e.target.value,
                                }));
                            }}
                            className="lInput"
                        />
                        <button onClick={onSubmit} className="lButton">
                            Login
                        </button>
                        <button onClick={registerUser} className="lButton">
                            Register
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}
export default Login